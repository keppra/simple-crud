rootProject.name = "Simple Crud"
include(
    ":app",
    ":features:add-user",
    ":features:base",
    ":features:common-ui",
    ":features:list-users",
    ":features:remove-user",
    ":features:summary",
    ":libraries:api",
    ":libraries:repositories"
)
