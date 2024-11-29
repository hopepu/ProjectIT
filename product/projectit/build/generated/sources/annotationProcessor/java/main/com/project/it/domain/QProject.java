package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -941896400L;

    public static final QProject project = new QProject("project");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> mno = createNumber("mno", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> pno = createNumber("pno", Long.class);

    public final NumberPath<Double> progress = createNumber("progress", Double.class);

    public final BooleanPath projectType = createBoolean("projectType");

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.project.it.constant.ProjectStatus> status = createEnum("status", com.project.it.constant.ProjectStatus.class);

    public final StringPath title = createString("title");

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public final StringPath version = createString("version");

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

