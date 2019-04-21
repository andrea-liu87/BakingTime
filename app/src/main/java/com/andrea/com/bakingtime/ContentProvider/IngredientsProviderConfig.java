package com.andrea.com.bakingtime.ContentProvider;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

@SimpleSQLConfig(
        name = App.INGREDIENTS_PROVIDER,
        authority = "ingredients_provider.authority",
        database = "ingredients.db",
        version = 1)
class IngredientsProviderConfig implements ProviderConfig {
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
