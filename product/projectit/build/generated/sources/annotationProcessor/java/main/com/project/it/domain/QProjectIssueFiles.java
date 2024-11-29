package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectIssueFiles is a Querydsl query type for ProjectIssueFiles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectIssueFiles extends EntityPathBase<ProjectIssueFiles> {

    private static final long serialVersionUID = -879382930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectIssueFiles projectIssueFiles = new QProjectIssueFiles("projectIssueFiles");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fno = createNumber("fno", Long.class);

    public final StringPath oldFileName = createString("oldFileName");

    public final QProjectIssue projectIssue;

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public QProjectIssueFiles(String variable) {
        this(ProjectIssueFiles.class, forVariable(variable), INITS);
    }

    public QProjectIssueFiles(Path<? extends ProjectIssueFiles> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectIssueFiles(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectIssueFiles(PathMetadata metadata, PathInits inits) {
        this(ProjectIssueFiles.class, metadata, inits);
    }

    public QProjectIssueFiles(Class<? extends ProjectIssueFiles> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.projectIssue = inits.isInitialized("projectIssue") ? new QProjectIssue(forProperty("projectIssue"), inits.get("projectIssue")) : null;
    }

}

