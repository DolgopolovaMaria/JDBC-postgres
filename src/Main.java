import db.DatabaseOperations;

public class Main {

  public static void main(String... args){
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // 1. Создать таблицу Student Колонки id, fio, sex, id_group
    // 2. Создать таблицу Group Колонки id, name, id_curator
    // 3. Создать таблицу Curator Колонки id, fio
    databaseOperations.createTables();

    // 4. Заполнить таблицы данными(15 студентов, 3 группы, 4 куратора)
    databaseOperations.fillTables();
    databaseOperations.printAllValues();

    // 5. Вывести на экран информацию о всех студентах включая название группы и имя куратора
    databaseOperations.printStudents();

    // 6. Вывести на экран количество студентов
    databaseOperations.printCountStudents();

    // 7. Вывести студенток
    databaseOperations.printGirlsStudents();

    // 8. Обновить данные по группе сменив куратора
    // 9. Вывести список групп с их кураторами
    databaseOperations.updateCurator(1, 4);

    // 10. Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)
    databaseOperations.printGroupStudents("Group1");
  }

}
