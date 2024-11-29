package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectIssue is a Querydsl query type for ProjectIssue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectIssue extends EntityPathBase<ProjectIssue> {

    private static final long serialVersionUID = 278899081L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectIssue projectIssue = new QProjectIssue("projectIssue");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> ino = createNumber("ino", Long.class);

    public final BooleanPath isFiles = createBoolean("isFiles");

    public final EnumPath<com.project.it.constant.ProjectPriority> priority = createEnum("priority", com.project.it.constant.ProjectPriority.class);

    public final QProject project;

    public final QProjectMember projectMember;

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final ListPath<ProjectIssueReply, QProjectIssueReply> replies = this.<ProjectIssueReply, QProjectIssueReply>createList("replies", ProjectIssueReply.class, QProjectIssueReply.class, PathInits.DIRECT2);

    public final EnumPath<com.project.it.constant.ProjectIssueStatus> status = createEnum("status", com.project.it.constant.ProjectIssueStatus.class);

    public final StringPath title = createString("title");

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public QProjectIssue(String variable) {
        this(ProjectIssue.class, forVariable(variable), INITS);
    }

    public QProjectIssue(Path<? extends ProjectIssue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectIssue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectIssue(PathMetadata metadata, PathInits inits) {
        this(ProjectIssue.class, metadata, inits);
    }

    public QProjectIssue(Class<? extends ProjectIssue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
        this.projectMember = inits.isInitialized("projectMember") ? new QProjectMember(forProperty("projectMember"), inits.get("projectMember")) : null;
    }

}

