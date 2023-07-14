# RustRecyclerCalculator-desktop
## Описание / Description
**[RU]**

Это калькулятор выходного количества ресурсов из переработчика в игре [Rust](https://store.steampowered.com/app/252490/Rust/).

Чтобы ввести количество конкретного предмета(компонента) - вам нужно нажать по нему ЛКМ и ввести количество в открывшемся окне. (не меньше 0 и не больше 99,999).

Далее жмите кнопку "Подсчитать" и программа выплюнет вам количество всех получившихся ресурсов на 4 строке (первые 3 под компоненты).

Если вам нужно что-то обнулить - нажмите кнопку "Сбросить" (она обнулит все поля), либо сделайте это вручную.

Так-же в калькуляторе присутствует доп. опция, под названием "Полная переработка", в чём её смысл?

```
Пример 1:
Полная переработка: выкл. (по-умолчанию)
С 10 компьютеров и 10 наборов для шитья вы получите:

500 метал. фрагментов, 10 мвк, 100 ткани, 30 микросхем и 20 веревок.
```

```
Пример 2:
Полная переработка: вкл. (опция находится внизу приложения)
С 10 компьютеров и 10 наборов для шитья вы получите:

600 лома, 500 метал. фрагментов, 40 мвк и 400 ткани.
```
Опция просто перерабатывает так-же компоненты которые могут выпасть с других компонентов.

---
**[EN]**

This is a calculator for the output amount of resources from the recycler in the game [Rust](https://store.steampowered.com/app/252490/Rust/).

To enter a certain amount of a item (component) - you need to make a constant LMB and enter the amount in the window that opens. (no less than 0 and no more than 99,999).

Next, press "Подсчитать" and the program will spit out the number of all available resources for you on 4 lines (the first 3 are under components).

If you need to reset something - click the "Сбросить" button (it will reset all fields to zero), or do it manually.

Also in the calculator of known add. an option called "Full recycle", what is its meaning?

```
Example 1:
Complete Recycling: Off (default)
With 10 computers and 10 sewing kits you will receive:

500 metal. frags., 10 HQM, 100 cloth, 30 tech parts and 20 ropes.
```

```
Example 2:
Complete Recycling: On (the option is at the bottom of the application)
With 10 computers and 10 sewing kits you will receive:

600 scrap, 500 metal. frags., 40 HQM and 400 cloth.
```
The option simply recycles the same components that can drop from other components.
## Функции / Features
+ [RU]
  + Подсчёт количества выходных ресурсов с переработчика
  + Графический интерфейс (JavaFX)
    + Графический интерфейс максимально приближен к реальному-игровому граф. интерфейсу
  + Автономность
  + Программа в данный момент поддерживает только русский язык!
+ [EN]
  + Counting the number of output resources from the recycler
  + GUI (JavaFX)
    + The GUI is approached a real game GUI
  + Autonomy
  + The program currently supports only the Russian language!
## Немного обо мне / A few details about myself
+ [RU]
  + Я не профессиональный разработчик и дизайнер
  + Я не силён в гитхабе, но пришлось разбираться чтобы вообще это залить сюда xD
  + В моём коде достаточно много дыр и "говнокода" который я буду пытаться исправлять
  + Если вы хотите связаться со мной, discord: Kepchyk1101
  + Мне это нравится
+ [EN]
  + I am not a professional developer and designer
  + I'm not good at github, but I had to post it here xD
  + There are a lot of holes and "bad code" in my code that I will try to fix
  + If you want to contact me, discord: Kepchyk1101
  + Мне это нравится
## Изображения / Images
![GUI](https://media.discordapp.net/attachments/745329037789888574/1129435117501296794/image.png?width=369&height=468)
## Уставнока / Downloads
https://github.com/Kepchyk1101/RustRecyclerCalculator-desktop/releases
## Зависимости / Dependencies
+ Java 17+
