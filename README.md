# RegistryRecommend

(Really this is more like RegistryGutCheck, but oh well.)

For now, the idea is that this service would take
- An existing user's list, and
- An existing user's order history

and filter the already purchased items out of the list, effectively preventing you from accidentally registering for a gift you already have (got married last year, that did happen)

## How to run
It's a basic grails 2.2.3 application with a single controller.

Just check out the RegistryCheckController or hit /checkMyRegistry