package com.sps.compose.data.repo

import com.sps.compose.data.Prefs.authUser
import com.sps.compose.data.model.User
import com.sps.compose.data.retrofit.RetrofitInterface
import com.sps.compose.data.retrofit.dto.UserDto
import com.sps.compose.data.room.dao.UserDao
import com.sps.compose.data.room.toUser
import com.sps.compose.data.room.toUserEntity
import com.sps.compose.util.GsonUtil.parseObject
import com.sps.compose.util.GsonUtil.processErrors
import com.sps.compose.util.Resource
import com.sps.compose.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class UserRepo(private val retrofitInterface: RetrofitInterface, private val userDao: UserDao) {
    suspend fun authenticate(phone: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.loading(null))
        try {
            val res = retrofitInterface.authenticate(phone, password)
            if (res.error == null) {
                val user: UserDto = parseObject(res.data, UserDto::class.java)

                // save user to database
                userDao.insert(user.toUserEntity())

                authUser.value = user.toUser()
                emit(Resource.success(user.toUser()))
            } else {
                emit(Resource.error(res.error, null))
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message, null))
        }
    }

    /**
     * get previous logged in account
     */
    fun getAuthenticatedUser() : Flow<User?> {
        return userDao.getUser().map { it?.toUser() }
//        if (user == null) emit(Resource.error("No user authenticated previously", null))
//        else emit(Resource.success(user.toUser()))
    }

    suspend fun deleteUser() {
        userDao.deleteAll()
    }

    fun signup(name: String, phone: String, email: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.loading(null))
        try {
            val res = retrofitInterface.signup(name, phone, email, password)
            log(res)
            if(res.errors != null) {
                throw Exception(processErrors(res.errors))
            }
            emit(Resource.success("user.toUser()"))
        } catch (e: Exception) {
            emit(Resource.error(e.message, null))
        }
    }


//    fun signup(name: String, phone: String, email: String, password: String): Flow<Resource<User>> = flow {
//        emit(Resource.loading(null))
//        try {
//            log("$name, $phone, $email, $password")
//            val user = retrofitInterface.signup(name, phone, email, password)
//            emit(Resource.success(user.toUser()))
//        } catch (e: Exception) {
//            emit(Resource.error(e.message, null))
//        }
//    }

}