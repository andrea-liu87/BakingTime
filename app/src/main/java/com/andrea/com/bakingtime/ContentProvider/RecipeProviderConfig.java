package com.andrea.com.bakingtime.ContentProvider;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;


@SimpleSQLConfig(
        name = App.RECIPE_PROVIDER,
        authority = "recipe_provider.authority",
        database = "recipe.db",
        version = 1)
class RecipeProviderConfig implements ckm.simple.sql_provider.annotation.ProviderConfig {
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
