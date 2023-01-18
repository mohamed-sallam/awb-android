package io.github.mohamed.sallam.awb.repo;

/**
 * Aggregate Root is the mother-ship entity inside the aggregate (Computer)
 * it is a common practice to have our repositories, `GroupRepository`,
 * `DeviceRepository`, `DetoxPeriodRepository`, only work with the entities that
 * are Aggregate Roots.
 *
 * `Device`, `Group`, `DetoxPeriod` are aggregate roots, so they implements
 * `IAggregateRoot` interface.
 * `DetoxPeriod`, `DetoxSchedule`, `DetoxSettings` form an aggregate and
 * `DetoxPeriod` is the aggregate root.
 *
 * @see <a href =” https://www.taithienbo.com/repository-pattern/”>Repository pattern</a>
 * @see <a href =”
 * https://stackoverflow.com/questions/1958621/whats-an-aggregate-root”>
 * What's an Aggregate Root?</a>
 *
 * @author Abdalrhman Hemida
 */
public interface IAggregateRoot {
}
