Название проекта: ContactBook_kusminova_sidorkina 
Разработчики: Сидоркина и Кусьминова Алины дата: 18.04.26

1. Явный Intent - Intent, в котором мы явно указываем компонент, который надо запустить. Например, у нас в коде: 
fun callPhoneNumber(context: android.content.Context, phoneNumber: String, notFound: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, notFound, Toast.LENGTH_SHORT).show()
    }
}

Явный Intent, что открывает нам приложение Телефон с уже указанным номером.

Неявный Intent - Intent, где не указываются конкретные компоненты. Мы задаем действия, данные или категорию, по которому пользователю показывается выбор из приложений, соответствующих этим данным. Например в коде:
fun shareText(context: Context, text: String, title: String, notFound: String)
{
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    val chooser = Intent.createChooser(sendIntent, title)
    if (sendIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(chooser)
    } else {
        Toast.makeText(context, notFound, Toast.LENGTH_SHORT).show()
    }
}
Данная функция открывает диалоговое окно, где мы можем отправить данный текст при помощи сообщения, имэйла и т.д.

2. Intent.createChooser() используется с неявным Intent. Этот метод отвечает за открытие диалогового окна выбора приложений.
3. Неяный Intent нельзя использовать для запуска Service начиная с Android 5.0. Вызов неявного Intent может быть небезопасным, потому что злоумышленник может создать вредоносный Service с вредоносным фильтром и перехватить данные, которые передаются нами.
4.
5. 4. Если вызвать setData() и setType() по отдельности, а не через setDataAndType(), то останется только последний параметр, так как он перекроет первый.
   5. PendingIntent - тот же Intent только с отложенным действием. Например, уведомление или планировщик задач.
   6. andoid:exported = "true" делает компонент доступным для запуска извне. Применяется только с неявным Intent.
   7. Раньше разработчики могли приватный компонент (явный Intent) разрешать для всех компонентов, но так как это небезопасно, то теперь обязательно надо прописывать "true" для неявного Intent и "false" для явного.
   8. Обязательно категорию, которую нужного добавить в Intent Filter, если Activity должна принимать неявный Intent - это DEFAULT. Он добавляет авытоматически ко всем гнеявным Intent CATEGORY_DEFAULT.
