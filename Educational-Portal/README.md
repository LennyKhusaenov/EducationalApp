# Educational Portal

### Requirements

- Implement a search for people by name or number of the document (use only one input for entering the search string).
- Use maven
- Refrain from using xml-settings

### Response


> To solve these problems, I chose the Spring Framework and associated libraries.
> As I used Mysql database.
> Determined using regexp search on the document or identity.
> Search in the database for pattern Search% - considering it allows the use of indexes.
> To communicate with a database model using Hibernate.
> Use annotations completely abstained from XML settings



### Paths:

* [Http://95.213.204.131/] - Home Page
* [Http://95.213.204.131/about] - Load Content from Github Page.
* [Http://95.213.204.131/generate] - Generate 30 Person entities with -05 doc per person.
* [Http://95.213.204.131/upload] - Files for Administrative input files.


### Deploy

- Execute fat .jar:

```Sh
$ Java -Dspring.profiles.active = production -jar project-1.0.0.BUILD-SNAPSHOT.jar
```


___
- Additional parameters generation PersonEntity:


```Sh
-Dgenerate.names = Alex,jack ...
-Dgenerate.surnames = Lebron,bryant ...
-Dgenerate.emails = Test@mail.ru,ops@ops.com ...
-Dgenerate.document.titles = DocForYou,SomeDoc ...
```

### Todos

* Would have to search using Elastic
* Add More Code Comments
* Create more Unit and functional tests


