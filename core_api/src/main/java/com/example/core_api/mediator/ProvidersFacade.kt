package com.example.core_api.mediator

import com.example.core_api.database.DatabaseProvider

interface ProvidersFacade : MediatorsProvider, AppProvider, DatabaseProvider
