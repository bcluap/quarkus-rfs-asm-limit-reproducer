# Reflection-free Jackson serializer fails for a DTO with 78+ properties (Quarkus 4.0.0.Beta2)

With `quarkus.rest.jackson.optimization.enable-reflection-free-serializers=true` (the default in Quarkus 4), the build
fails when a REST endpoint returns a type with 78 or more object-typed properties:

```
Build step io.quarkus.resteasy.reactive.jackson.deployment.processor.ResteasyReactiveJacksonProcessor#handleEndpointParams
threw an exception: org.objectweb.asm.LimitExceededException: Too many operations
```

`Big` has 78 public fields of type `Item` (a plain 8-field POJO). `BigResource` returns it.

## Reproduce

Requires JDK 21+ and Maven.

```shell
mvn package                         # Quarkus 4.0.0.Beta2: fails with LimitExceededException
python3 gen.py 77 && mvn package    # 77 fields: builds
python3 gen.py 78 && mvn package    # 78 fields: fails again
mvn package -Dq.version=3.40.1      # Quarkus 3.40.1 (ASM 9.10.1): builds, even with 120 fields
mvn package -Dquarkus.rest.jackson.optimization.enable-reflection-free-serializers=false   # builds
```

`gen.py N` regenerates `Big.java` with N fields.

## Results

| Fields | Quarkus 4.0.0.Beta2 (ASM 9.11) | Quarkus 3.40.1 (ASM 9.10.1) |
|---|---|---|
| 77 | builds | builds |
| 78 | **fails** | builds |
| 120 | **fails** | builds |
