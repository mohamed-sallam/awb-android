package io.github.mohamed.sallam.awb.repo;
/**
 * Repository pattern apply a design principle 'separation of concerns' which
 * makes our classes unit testable. It applies Principle of Dependency Inversion
 * (or code to abstractions, not concretions), To make our code more robust to
 * changes.
 * Repositories classes are responsible for handling and implementing operations,
 * and adding the full logic to execute a specific operation by using DAOs methods.
 *
 * Using 'lamda expression' over the whole methods of repositories to optimize
 * the code. Lambda Expressions were added in Java 8.
 *
 * Why using lamda expression?
 * Makes our code more readable and shorter. Instead of instantiation an
 * anonymous inner class that implements the abstract method run() on the
 * interface 'Runnable', we use lamdad expression to do that.
 *
 * For example: instead of writing
 * ```
 * UserDatabase.databaseWriteExecutor.execute(new Runnable() {
 *             @Override
 *             public void run() {
 *                 detoxPeriodDao.insert(detoxPeriod);
 *             }
 *         });
 * ```
 * we write:
 * ```
 * UserDatabase.databaseWriteExecutor.execute(
 *                 () -> detoxPeriodDao.insert(detoxPeriod)
 *         );
 * ```
 * Source: https://code.tutsplus.com/tutorials/
 * java-8-for-android-cleaner-code-with-lambda-expressions--cms-29661
 *
 * Generics used to provide compile-time type checking and removing risk of
 * `ClassCastException` that was common while working with collection classes.
 * {@link IAggregateRoot} to define the aggregate roots to extend.
 *
 * @author Abdalrhman Hemida
 */
public interface IRepository<T extends IAggregateRoot> {

    /**
     * Inserts an object into the database.
     *
     * @param entity object to be inserted into database its type variant from
     * implementation to another of the abstract method.
     */
    void insert(T entity);
}
