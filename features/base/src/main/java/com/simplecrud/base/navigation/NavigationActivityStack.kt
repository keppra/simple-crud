package com.simplecrud.base.navigation

import com.simplecrud.base.presentation.BaseActivity

interface NavigationActivityStack {
    fun addActivityToStack(activity: BaseActivity)
    fun getActivityStack(): List<BaseActivity>
    fun deleteActivityFromStack(activity: BaseActivity)
    fun finishActivityFromStack(activity: BaseActivity)
    fun clearStack()
}

class NavigationActivityStackImp: NavigationActivityStack {

    private var activityList = mutableListOf<BaseActivity>()

    override fun addActivityToStack(activity: BaseActivity) {
        activityList.add(activity)
    }

    override fun getActivityStack(): List<BaseActivity> =
        activityList

    override fun deleteActivityFromStack(activity: BaseActivity) {
        activityList.remove(activity)
    }

    override fun finishActivityFromStack(activity: BaseActivity) {
        val index = activityList.indexOf(activity)
        if (index != -1) {
            activityList[index].finish()
        }
    }

    override fun clearStack() {
        activityList.clear()
    }

}