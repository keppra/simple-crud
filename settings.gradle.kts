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
    ":libraries:k-progress-hud",
    ":libraries:recyclerview-renders",
    ":libraries:repositories",
    ":libraries:utils"
)
