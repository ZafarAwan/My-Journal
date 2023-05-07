package com.bbox.myjournal.repositories.local.factories

interface IJournalFactory {
    fun <T : IRepository> create(modelClass: Class<T>): T
}