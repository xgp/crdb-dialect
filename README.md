# crdb-dialect

A Hibernate (6) dialect for CRDB that removes **all** `escape '...'` clauses in SQL LIKE/ESCAPE

## notes

- https://www.cockroachlabs.com/docs/stable/known-limitations#using-like-escape-in-where-and-having-constraints
- https://github.com/steveperkins/hibernate6-h2-like-fix
- https://docs.jboss.org/hibernate/orm/6.2/javadocs/org/hibernate/dialect/CockroachSqlAstTranslator.html
- https://github.com/hibernate/hibernate-orm/blob/6.2/hibernate-core/src/main/java/org/hibernate/dialect/CockroachSqlAstTranslator.java#L131
- https://discourse.hibernate.org/t/why-does-hibernate-6-automatically-escape-backslashes-when-rendering-like-predicates/7420
- https://discourse.hibernate.org/t/erroneous-escape-being-added-to-generated-sql-with-a-jparepository-findall-example/9353
- https://discourse.hibernate.org/t/it-is-necessary-to-write-redundant-escape-after-every-like-expression/9079/5
- https://discourse.hibernate.org/t/incorrect-escape-added-to-sql-for-h2-in-oracle-mode/8372/10



