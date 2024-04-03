package tests.dashboardtests.model;

public record PersonModelData(String username,
                              String password,
                              String monthlyEarning,
                              String annualEarning,
                              String profitMargin,
                              String availableInventory,
                              String searchKeyword,
                              int searchResultsCount) {
}
