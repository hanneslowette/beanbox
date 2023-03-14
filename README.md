# BeanBox

This library is a testing library for Java EE7 specification applications. This does not conform
to the EE7 specifications fully but is aiming to support these:

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