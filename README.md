# An Kotlin compiler template example

A small example of a Kotlin compiler plugin to calculate the sum of sized of 
the properties in data classes powered by [arrow-meta](https://github.com/arrow-kt/arrow-meta).

Tasks:
- implement the plugin's body in the `AddShallowSizeMethod` plugin (replace all TODOs)
- rewrite tests to parameterized tests in the `shalowSizePluginTest` module
- add an GitHub action to build the plugin, run the tests and check the code quality