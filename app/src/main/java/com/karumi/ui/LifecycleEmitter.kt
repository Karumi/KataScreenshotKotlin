package com.karumi.ui

val lifeCycleLinker = LifecycleLinker()

class LifecycleLinker : LifecycleEmitter {
    private val receivers = ArrayList<LifecycleReceiver>()

    override fun registerToLifecycle(receiver: LifecycleReceiver) {
        receivers.add(receiver)
    }

    override fun update() {
        receivers.forEach(LifecycleReceiver::update)
    }

    override fun initialize() {
        receivers.forEach(LifecycleReceiver::initialize)
    }
}

interface LifecycleEmitter {
    fun initialize()
    fun registerToLifecycle(receiver: LifecycleReceiver)
    fun update()
}

interface LifecycleReceiver {
    fun initialize()
    fun update()
}
