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