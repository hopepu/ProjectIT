package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectIssueReply is a Querydsl query type for ProjectIssueReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectIssueReply extends EntityPathBase<ProjectIssueReply> {

    private static final long serialVersionUID = -868415775L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectIssueReply projectIssueReply = new QProjectIssueReply("projectIssueReply");

    public final StringPath content = createString("content");

    public final QProjectIssue projectIssue;

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public final StringPath writer = createString("writer");

    public QProjectIssueReply(String variable) {
        this(ProjectIssueReply.class, forVariable(variable), INITS);
    }

    public QProjectIssueReply(Path<? extends ProjectIssueReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectIssueReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectIssueReply(PathMetadata metadata, PathInits inits) {
        this(ProjectIssueReply.class, metadata, inits);
    }

    public QProjectIssueReply(Class<? extends ProjectIssueReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.projectIssue = inits.isInitialized("projectIssue") ? new QProjectIssue(forProperty("projectIssue"), inits.get("projectIssue")) : null;
    }

}

