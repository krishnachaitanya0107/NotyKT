/*
 * Copyright 2020 Shreyas Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shreyaspatil.noty.data.database

import dev.shreyaspatil.noty.data.database.table.Notes
import dev.shreyaspatil.noty.data.database.table.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Initializes dev.shreyaspatil.noty.data.database connection with application
 */
fun initDatabase(databaseConfig: DatabaseConfig) {
    val tables = arrayOf(Users, Notes)

    Database.connect(
        url = with(databaseConfig) { "jdbc:postgresql://$host:$port/$name" },
        driver = "org.postgresql.Driver",
        user = databaseConfig.user,
        password = databaseConfig.password
    )

    transaction {
        SchemaUtils.createMissingTablesAndColumns(*tables)
    }
}