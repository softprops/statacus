# statacus

        ___       ___       ___       ___       ___       ___       ___       ___
       /\  \     /\  \     /\  \     /\  \     /\  \     /\  \     /\__\     /\  \
      /::\  \    \:\  \   /::\  \    \:\  \   /::\  \   /::\  \   /:/ _/_   /::\  \
     /\:\:\__\   /::\__\ /::\:\__\   /::\__\ /::\:\__\ /:/\:\__\ /:/_/\__\ /\:\:\__\
     \:\:\/__/  /:/\/__/ \/\::/  /  /:/\/__/ \/\::/  / \:\ \/__/ \:\/:/  / \:\:\/__/
      \::/  /   \/__/      /:/  /   \/__/      /:/  /   \:\__\    \::/  /   \::/  /
       \/__/               \/__/               \/__/     \/__/     \/__/     \/__/


A [scalaly][scala] [statsd][sd] client for [netting](http://en.wikipedia.org/wiki/Butterfly_net) those wee stats collecting in the wind, or learn to instrument by playing the counter.

# usage

    import statatus._

    val stats = Stats("localhost") /*defaults to port 8125*/

    // increment fun by 100
    stats inc("fun", 100)

    // collect a sampling of 25% of a set keys incrementing by 6
    stats.count(by = 6, ratio = 0.25)("login", "logout", "logcabin")

    // if it moves, time it
    stats.time("bunny", 320)

    // create a reusable counter
    val cntr = stats.counter("cars.red")
    when(redCarPasses) cntr.inc

thanks http://www.network-science.de/ascii/

Doug Tangren (softprops) 2011

[sd]: https://github.com/etsy/statsd#readme
[scala]: http://www.scala-lang.org/
