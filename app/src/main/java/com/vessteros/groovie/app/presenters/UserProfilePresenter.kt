package com.vessteros.groovie.app.presenters

import android.widget.Toast
import com.vessteros.groovie.app.activities.UserProfileActivity
import com.vessteros.groovie.app.helpers.issues.InputIssue
import com.vessteros.groovie.app.helpers.utils.dataExists
import com.vessteros.groovie.app.helpers.utils.isEmpty
import com.vessteros.groovie.app.interceptors.ProfileInterceptor
import com.vessteros.groovie.app.models.GUser
import com.vessteros.groovie.app.models.cases.UpdaterCase
import com.vessteros.groovie.app.models.cloud.requests.Requests.*
import com.vessteros.groovie.app.models.entities.User
import com.vessteros.groovie.app.services.db.repositories.UserRepository
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfilePresenter(val view: UserProfileActivity) : BasePresenter {
    var issue: InputIssue? = null

    private val userRepository = UserRepository()
    private val interceptor = ProfileInterceptor(this)

    fun getUser() = interceptor.getUser()

    fun onUserFound(user: User) = view.setViews(user)

    fun saveChanges(dataCase: UpdaterCase) {
        searchForIssues(dataCase)

        if (issue.isEmpty()) save(dataCase) else view.issue(issue!!)
    }

    private fun save(dataCase: UpdaterCase) = when {
        dataCase.dataExists() -> {
            val request = interceptor.saveProfileChanges(
                ProfileChangesRequest(
                    GUser.id!!,
                    dataCase.name,
                    dataCase.lastName,
                    dataCase.login,
                    dataCase.currentPassword,
                    dataCase.newPassword
                )
            )
        }

        else -> back()
    }

    fun onProfileSaved() = run {
        Toast.makeText(view, "Изменения сохранены", Toast.LENGTH_LONG).show()
        back()
    }

    fun back() = view.returnToMain()

    fun onProfileSaveFail() = run {
        Toast.makeText(view, "Ошибка при сохранении", Toast.LENGTH_LONG).show()
    }

    private fun searchForIssues(dataCase: UpdaterCase) = when {
        !dataCase.newPassword.isNullOrEmpty() && dataCase.currentPassword.isNullOrEmpty() -> {
            issue = InputIssue.EmptyCurrentPasswordField.apply {
                executable = {
                    Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                    view.userCurrentPassword.also {
                        it.setTextColor(this.textColor)
                        it.setBackgroundColor(this.inputColor)
                    }
                }
            }
        }

        else -> { }
    }

    fun setIssue() {
        issue = InputIssue.CurrentPasswordIsIncorrect.apply {
            executable = {
                Toast.makeText(view, this.message, Toast.LENGTH_LONG).show()

                view.userCurrentPassword.also {
                    it.setTextColor(this.textColor)
                    it.setBackgroundColor(this.inputColor)
                }
            }
        }
    }
}