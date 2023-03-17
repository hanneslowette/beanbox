# BeanBox

Tiny "mildly Java EE7 specification compliant" testing library for Java EE 7 and EJB applications. Does not handle remote EJBs.

# Features

### Current
- Programming model
  - Managed beans implemented by a Java class
    - @PostConstruct lifecycle callback
- Dependency injection and lookup
  - Field, constructor and initializer/setter injection
  - Type-safe resolution
  - Injection point metadata
- Scopes and contexts
- Persistence context
  - test-level transaction management
- Mockito support
  - Inject mocks into the classes
  - Allow @Spy instances
### Planned
- Programming model
  - Managed beans implemented by a Java class
    - @PreDestroy lifecycle callback
    - Producer methods and fields, disposers
    - Qualifiers
- Decorators
- Mockito support
  - Inject mocks into the classes
  - Allow @Spy instances



# Usage

### Dependencies
maven:
```xml
<dependency>
  <groupId>com.colruytgroup</groupId>
  <artifactId>beanbox</artifactId>
  <version>${beanbox.version}</version>
</dependency>

```

gradle: 

```groovy
testImplementation(group: 'com.colruytgroup', name: 'beanbox', version: '+')
```

### BeanBoxRunner
You can use the BeanBoxRunner class to initialize the test cases by adding the annotation at the top

```java
import com.colruytgroup.beanbox.BeanBoxRunner;

@RunWith(BeanBoxRunner.class)
public class TestClass {
    
}
```

If you wish to enable transactions you will have to use the @TransactionalTest annotation to the test method. BeanBox will
then open and close the transaction in the scope of the method with the persistence context names as optional parameters


```java

@RunWith(BeanBoxRunner.class)
public class TestClass {
    
    @TransactionalTest("persistence_context_name")
    public void testTransactional() {
  
    }
  
}
```

If you want more control over the persistence context you can also add it as a parameter to test method with @PersistenceContextParam
or inject the persistence context manually and then control it from there in the test cases

```java

@RunWith(BeanBoxRunner.class)
public class TestClass {
    
    @PersistenceContext(unitName = "test")
    private EntityManager manager;
    
    @Test
    public void testTransactional(@PersistenceContextParam("test") EntityManager manager) {
        
    }
    
}
```


### @Before

BeanBox also supports injecting the class yourself without the runner as follows

```java
public class TestClass {
    
    @Before
    public void initializeBeanBox() {
      BeanBox.initialize(this);
    }
  
}
```

For the transactions you will need to open and close the transactions manually

```java
public class TestClass {
    
    @Before
    public void initializeBeanBox() {
      BeanBox.initialize(this);
      BeanBox.beginTransaction("test_context");
    }
  
    @After
    public void initializeBeanBox() {
      BeanBox.commitTransaction("test_context");
    }
  
}
```
