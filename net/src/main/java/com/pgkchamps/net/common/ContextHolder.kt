package com.pgkchamps.net.common

import android.content.Context
import java.lang.ref.WeakReference


//             Context - простыми словами это окружение, ситуация и параметры.
object ContextHolder {
    private var context: WeakReference<Context>? =
        null  // WeakReference - дает контексту возможность очиститься при закрытии приложения.
    // Стенд (переходник) для тестов. Сюда перед тестом в телефоне мы подсовываем фейковый контекст, чтобы запустить код без телефона
    fun init(actualContext: Context) {
// Если в гараже пусто — заселяем туда контекст всего приложения.
// А если там уже что-то лежит, то ничего не делаем, все гуляют лесом.
        if (context == null) {
            context = WeakReference(actualContext.applicationContext)
        }
    }

    // Вечная распредкоробка. Любой блок кода может вызвать этот метод, чтобы быстро узнать погоду/язык/ресурсы системы
    fun getContext(): Context {
        return context?.get() ?: throw Exception("no init context")
    }
}
// функция датьКонтекст(без параметров) : Контекст {
// вернуть возможно контекст.датьЕго() ?: если исключение (не получилось)}
