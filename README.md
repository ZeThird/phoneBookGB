# Телефонная книга
Это реализация структуры телефонной книги на языке Java с помощью HashMap.
## Устройство
### Скрытый phoneBook
Хранилищем всех номеров является этот HashMap. Ключи в нём - имена, а значения - ArrayList'ы с номерами, представленными числами. Для получения этого HashMap'а есть метод getPhoneBook, который его возвращает.
### Добавление
Для этого есть метод add, который принимает имя и номер. Если имени ещё не было, то он добавляет его в phoneBook вместе с номером, а если было, то добавляет номер к этой же записи.
### Вывод телефонной книги
Для вывода тоже есть свой метод - printSorted, который выводит в терминал все имена и телефоны, сортированные по количеству телефонных номеров у пользователя по убыванию.
#### Технические детали сортировки
Такая сортировка вывода достигнута с помощью дополнительной структуры данных - ArrayList, содержащего структуры Set (HashSet на самом деле, множества) с именами. Множества содержат имена, количество телефонов у которых соответсветствует их индексу в ArrayList + 1. Set позволяет быстро убирать и находить имена, а ArrayList добавление нового множество для большего количества телефонных номеров, если остальных окажется недостаточно.

Каждый раз, когда номер добавляется или убирается у конкретного имени, это самое имя перемещается в другое множество, сохраняя сортированность. При добавлнии или удалении имен, они тоже добавляются или удаляются из этой структуры.

Тогда, когда вызван метод PrintSorted, он проходится как раз по этой струтуре, от конца до начала (чтобы обеспечить вывод, сортированный по *убыванию*, а не по возрастанию) и бёрет имена и выводит их вместе с номерами, взятыми из PhoneBook.
### Удаление
Есть пара методов для удаления записей - removeUser и removeNumber. Первый берёт имя, и полностью удаляет его вместе с номерами телефонов. Второй же берёт номер, а потом имя, и удаляет его у этого имени. Если у пользователя только один номер телефона, то также удаляется и он сам.
