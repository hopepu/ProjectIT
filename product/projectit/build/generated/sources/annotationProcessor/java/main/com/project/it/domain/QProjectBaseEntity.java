package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectBaseEntity is a Querydsl query type for ProjectBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QProjectBaseEntity extends EntityPathBase<ProjectBaseEntity> {

    private static final long serialVersionUID = 771597156L;

    public static final QProjectBaseEntity projectBaseEntity = new QProjectBaseEntity("projectBaseEntity");

    public final DatePath<java.time.LocalDate> regDate = createDate("regDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> updateDate = createDate("updateDate", java.time.LocalDate.class);

    public QProjectBaseEntity(String variable) {
        super(ProjectBaseEntity.class, forVariable(variable));
    }

    public QProjectBaseEntity(Path<? extends ProjectBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectBaseEntity(PathMetadata metadata) {
        super(ProjectBaseEntity.class, metadata);
    }

}

