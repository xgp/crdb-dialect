package io.phasetwo.crdb.hibernate;

import lombok.extern.jbosslog.JBossLog;
import org.hibernate.dialect.CockroachSqlAstTranslator;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.sql.ast.tree.Statement;
import org.hibernate.sql.ast.tree.predicate.LikePredicate;
import org.hibernate.sql.exec.spi.JdbcOperation;

@JBossLog
public class CrdbEscapeTranslator<T extends JdbcOperation> extends CockroachSqlAstTranslator<T> {

  public CrdbEscapeTranslator(SessionFactoryImplementor sessionFactory, Statement statement) {
    super(sessionFactory, statement);
  }

  @Override
  public void visitLikePredicate(LikePredicate likePredicate) {
    // Custom implementation because CockroachDB uses backslash as default escape character
    likePredicate.getMatchExpression().accept(this);
    if (likePredicate.isNegated()) {
      appendSql(" not");
    }
    if (likePredicate.isCaseSensitive()) {
      appendSql(" like ");
    } else {
      appendSql(WHITESPACE);
      appendSql(getDialect().getCaseInsensitiveLike());
      appendSql(WHITESPACE);
    }
    likePredicate.getPattern().accept(this);
    /* DO NOTHING
    if ( likePredicate.getEscapeCharacter() != null ) {
      appendSql( " escape " );
      likePredicate.getEscapeCharacter().accept( this );
    }
    else {
      appendSql( " escape ''" );
    }
    */
  }

  /*
  @Override
  public void visitLikePredicate(LikePredicate likePredicate) {
    LikePredicate mutatedPredicate = new LikePredicate(likePredicate.getMatchExpression(), likePredicate.getPattern(), new EscapeExpression("\\"), likePredicate.isNegated(), likePredicate.isCaseSensitive(), likePredicate.getExpressionType());
    super.visitLikePredicate(mutatedPredicate);
  }

  class EscapeExpression implements Expression {

    private final String escapeCharacter;

    EscapeExpression(String escapeCharacter) {
      this.escapeCharacter = escapeCharacter;
    }

    @Override
    public JdbcMappingContainer getExpressionType() {
      return null;
    }

    @Override
    public void accept(SqlAstWalker sqlTreeWalker) {
      ((SqlAppender) sqlTreeWalker).appendSql("'" + escapeCharacter + "'");
    }

    @Override
    public String toString() {
      return escapeCharacter;
    }
  }
  */
}
