package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectEvent is a Querydsl query type for ProjectEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectEvent extends EntityPathBase<ProjectEvent> {

    private static final long serialVersionUID = 275280714L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectEvent projectEvent = new QProjectEvent("projectEvent");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final DatePath<java.time.LocalDate> end = createDate("end", java.time.LocalDate.class);

    public final NumberPath<Long> eno = createNumber("eno", Long.class);

    public final BooleanPath isFisrt = createBoolean("isFisrt");

    public final QProject project;

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final DatePath<java.time.LocalDate> start = createDate("start", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public QProjectEvent(String variable) {
        this(ProjectEvent.class, forVariable(variable), INITS);
    }

    public QProjectEvent(Path<? extends ProjectEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectEvent(PathMetadata metadata, PathInits inits) {
        this(ProjectEvent.class, metadata, inits);
    }

    public QProjectEvent(Class<? extends ProjectEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
    }

}

