
                                           1ToOne


********Do read Technical Specifications section: Adding support for more languages
                                                  To Add new language support
                                                  Add Support for different outputType

To understand how the project is open to extension from adding languages point of view and extending outputType as well


Author
--------------
1) Mukul Goel (email: goel.mukul1989@gmail.com)


What is it?
--------------

The 1ToOne is a java program that converts an input number
to its sentence representation in the language specified by the user.

Language by default is English

Eg:

 Given 1 output one
 Given 21 output twenty one
 Given 105 output one hundred and five
 Given 110105 output one hundred and ten thousand one hundred and five
 Given 1000099 output one million and ninety nine
 Given 1000100 output one million one hundred
 Given 56945781 output fifty six million nine hundred and forty five thousand seven
hundred and eighty one
 etc..


Features
------------------------
Converts integer number to its sentence representation
Converts negative number to sentence representation as well (Test cases added)

The Latest Version
----------------------

Details of the project and the latest version can be found at:

https://github.com/mukulgoel1989/1ToOne


Installation
-----------------------
No installation is required. It is a java program and can be compiled and executed on any Java 1.6 or later JVM


Usage
-----------------------
WordifiedNumberClient class is the client and consumes the methods provided by the class
This class has the main method and expects the user to enter the langauge name at run time

Steps
1) Run the WordifiedNumberClient class
2) When asked for langauge input enter the language (Supported language(s) : English))
3) Result is printed to screen.

Upcoming Releases
--------------------
Upcoming release has the following features
* Support for more languages
* Support to allow for different output formats for a language

Eg: presently the following format is used (for English)
 Given 1 output one
 Given 21 output twenty one
 Given 105 output one hundred and five
 Given 110105 output one hundred and ten thousand one hundred and five
 Given 1000099 output one million and ninety nine
 Given 1000100 output one million one hundred
 Given 56945781 output fifty six million nine hundred and forty five thousand seven
hundred and eighty one
 etc..

Support to print output in following format to be added

 Given 1 output first
 Given 2 output second
 Given 21 output twenty first
 Given 105 output one hundred and fifth
 etc