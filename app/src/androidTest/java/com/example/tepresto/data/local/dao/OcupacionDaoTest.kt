package com.example.tepresto.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.tepresto.data.local.TePrestoDb
import com.example.tepresto.data.local.entity.OcupacionEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class OcupacionDaoTest {

    private  lateinit var database: TePrestoDb
    private lateinit var dao : OcupacionDao

    @Before
    fun setUp() {
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TePrestoDb::class.java
        ).allowMainThreadQueries().build()

        dao= database.ocupacionDao
    }

    @Test
    fun Insert_Ocupacion_Is_Success() = runBlocking {
        //arrange
        val id=1
        val ocupacionInsertar = OcupacionEntity(
            ocupacionId = id,
            descripcion = "Ingeniero Sistemas",
            sueldo = 0.0
        )

        //act
        dao.insert(ocupacionInsertar)
        var ocupacionBuscada = dao.find(id)

        //ocupacionBuscada=  ocupacionBuscada?.copy(sueldo = 10.0)
        //assert
        Truth.assertThat(ocupacionBuscada).isEqualTo(ocupacionInsertar)

    }

    @After
    fun tearDown() {
        database.close()
    }
}