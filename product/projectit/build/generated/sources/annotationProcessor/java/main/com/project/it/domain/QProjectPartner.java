package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectPartner is a Querydsl query type for ProjectPartner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectPartner extends EntityPathBase<ProjectPartner> {

    private static final long serialVersionUID = -1159630472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectPartner projectPartner = new QProjectPartner("projectPartner");

    public final StringPath email = createString("email");

    public final QInfoPartners infoPartners;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final NumberPath<Long> ppno = createNumber("ppno", Long.class);

    public final QProject project;

    public final StringPath request = createString("request");

    public QProjectPartner(String variable) {
        this(ProjectPartner.class, forVariable(variable), INITS);
    }

    public QProjectPartner(Path<? extends ProjectPartner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectPartner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectPartner(PathMetadata metadata, PathInits inits) {
        this(ProjectPartner.class, metadata, inits);
    }

    public QProjectPartner(Class<? extends ProjectPartner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.infoPartners = inits.isInitialized("infoPartners") ? new QInfoPartners(forProperty("infoPartners")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

