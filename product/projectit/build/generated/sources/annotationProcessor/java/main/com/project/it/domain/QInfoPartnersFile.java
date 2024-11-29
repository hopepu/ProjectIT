package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInfoPartnersFile is a Querydsl query type for InfoPartnersFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInfoPartnersFile extends EntityPathBase<InfoPartnersFile> {

    private static final long serialVersionUID = 1726014558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInfoPartnersFile infoPartnersFile = new QInfoPartnersFile("infoPartnersFile");

    public final QProjectBaseEntity _super = new QProjectBaseEntity(this);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fno = createNumber("fno", Long.class);

    public final QInfoPartners infoPartners;

    public final StringPath oldFileName = createString("oldFileName");

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    //inherited
    public final DatePath<java.time.LocalDate> updateDate = _super.updateDate;

    public QInfoPartnersFile(String variable) {
        this(InfoPartnersFile.class, forVariable(variable), INITS);
    }

    public QInfoPartnersFile(Path<? extends InfoPartnersFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInfoPartnersFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInfoPartnersFile(PathMetadata metadata, PathInits inits) {
        this(InfoPartnersFile.class, metadata, inits);
    }

    public QInfoPartnersFile(Class<? extends InfoPartnersFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.infoPartners = inits.isInitialized("infoPartners") ? new QInfoPartners(forProperty("infoPartners")) : null;
    }

}

