# BeanBox

Tiny "mildly Java EE7 specification compliant" testing library for EJB applications. Does not handle remote EJBs.

# Supported features

- Programming model
  - Managed beans implemented by a Java class
    - @PostConstruct and @PreDestroy lifecycle callbacks
    - Producer methods and fields, disposers
    - Qualifiers
- Dependency injection and lookup
  - Field, constructor and initializer/setter injection
  - Type-safe resolution
  - Programmatic lookup via javax.enterprise.inject.Instance
  - Injection point metadata
- Scopes and contexts
- Decorators
- Persistence context
  - test-level transaction management
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
@TransactionalTest("persistence_context_name")
public void testTransactional() {
    
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