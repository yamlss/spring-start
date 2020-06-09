# Let's get started

1. Go here https://www.jetbrains.com/community/education/#students and create a student account with your .edu email address

2. Install the appropriate version of java from https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

3. Download and activate "IntelliJ IDEA"

4. Create a github account (or log into existing) at https://www.github.com

5. Download MongoDB from https://fastdl.mongodb.org/win32/mongodb-win32-x86_64-2012plus-4.2.7-signed.msi

6. Under your C drive (C://) create a `data` folder and then inside of that, create a `db` folder

7. Open up command prompt, enter `cd C:\Program Files\MongoDB\Server\4.2\bin` and then enter `mongod`

8. Press green connect button in Mongo Compass to preview database

# So how does any of this work?

Good question. I think one of the hardest things to do might be to break down a complicated concept that one understands
into something anyone else will be able to pick up and know what's going on. So I'll try my best!

This project is a full stack Spring Boot application. Let's break that down.

**full-stack**: combination of both front end and back end

**front-end**: the website part of an application that anyone is able to visit, see, and interact with

**back-end**: the logic part of an application that takes input from the user (usually from the front-end) and does the
business logic of an application like talking to databases and stuff

**Spring boot**: a feature of the Spring framework, that is able to package up your entire application into a .jar file
to be deployed

Hopefully that gave you a high level overview of what this application is, technically.

## Okay tim, this is the CEO of library company inc, if you don't make me a library application that can manage our
books you lose 1000 life points

Oh crap, okay, where do we start? [start.spring.io](https://start.spring.io).

![Imgur](https://i.imgur.com/SOh68ED.png)

Spring is a company that has created its own very popular Java framework, which is open source meaning anyone can look
at the code and contribute to it. They have a website that makes it super simple to create your own spring project so
you can hit the ground running pretty quickly, and I'm just showing you what settings I used for this project.

When you look at it at first, it will be pretty empty.

### pom.xml

The important files/folders will be these:

**pom.xml**: this is the heart + soul of your application. It's written in an ugly language (xml) but you'll get used
to it.

`<parent>`: the repository you are inheriting from, most projects are going to take in some basic configuration stuff
that's not really important to understand now, but it's similar to the following concept:

```python3
class Parent:
    def __init__(self, a):
        self.a = a;

def Child(Parent): # Child INHERITS Parent
    def __init__(self, b):
        super().__init__("a")
        self.b = b

>>> child = Child("b")
>>> child.a # a isn't defined anywhere in Child, but since it inherits from Parent...
"a"
```

`<groupId>` + `<artifactId>`: together collectively uniquely identify your project

`<version>`: which version of your own project you are working on

`<name>`: your project's name

`<properties>`: global variables that can be accessed in the rest of the xml file

`<dependencies>`: all the libraries and whatever code you're importing

`<plugins>`: sort of like dependencies, except they have more to do with running the actual project or other commands

Hopefully this gets you a tad more comfortable with reading a pom.xml file because it really isn't that scary. If you
ever forget it's totally easy just to google something like "what is ... pom.xml" and someone has probably answered it.

Anyway, hopefully it's clear how this is the core of the application - this is one of the first things any programmer
will reference to get an idea for the application.

### DemoApplication

**src/main/java/com/example/demo/DemoApplication**: This is the entry point of your application. If you're using
IntelliJ, you'll see that it's being recognized as a runnable. Whenever you want to start your application, it starts
here.

If you run it without adding anything though, nothing crazy will happen. Spring will run `DemoApplication`, load the
ApplicationContext, and then power down the application because there's nothing to do.

If you look at some of the files we added, they add things that latch onto Spring so it knows not to power down.

### DemoController

Let's start with `controllers/DemoController`. Here, you have a Java class that is annotated with `@Controller` so
Spring tags it as a controller. A Controller class sets up endpoints for your application, so it basically opens up
URL/website paths you can visit to trigger methods.

For example, look at the method `getBooks`. You can see that this method is triggered by a `GET` request to the URL
`/getBooks`. This means when anyone visits your website + `/getBooks` they will trigger this function.

If we take a closer look at the function, we can infer that it's getting books from `DemoService` and returning them.

If you start up the application and visit your website ([localhost:8080/getBooks](localhost:8080/getBooks)), you'll
see the output of the function! It will probably be empty because there's nothing in the database, but we can fix that.

The method `checkout` adds a new checked out book to our database. Now this method is a little bit more complicated,
compared to `getBooks`. This time, there are two `@RequestParam` arguments. This just means that this URL will need
additional information from us. It will need a upc and a library id, to identify the book and the person checking out
the book. To provide this information, we do this:
[localhost:8080/checkout?upc=123&libraryId=456](localhost:8080/checkout?upc=123&libraryId=456).

`?` tells the URL that we want to pass in some parameters/arguments and then we can list them with `&` between each.

This, according to our code, saves a copy of this object to our database. Now if we go to the first URL, we should see
this book in our system!

Believe it or not, the vast majority of applications on the internet are this simple. Taking data from a user, storing
it, and giving it back whenever you want. What you've just accomplished is what most developers do on a regular basis.

### DemoService + DemoRepository

Another question you might have is regarding the `DemoController`'s constructor. Why does it take a `DemoService`, you
might be asking. Good question! This is called dependency injection. Don't worry too much about it, but basically when
Spring runs `DemoApplication`, it's going to try to instantiate anything you've defined, which in our case is a
controller class. Well, in order to interact with our database through the controller, we need to be able to interact
with `DemoService` (intermediary between controller and repository). So by defining the `DemoService` in the constructor
you're just telling Spring that you need a `DemoService` before you can run any code in `DemoController`. Respectively,
if you look at `DemoService`, you also need a `DemoRepository` before you can run any code in `DemoService`. They're
just instructions for Spring and nothing more.

### Next

this is a lot of information, so I won't cover the "front end" part of this project yet (resources folder) but it will
allow you to create a website to use the API we just created.
