package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectAlarm is a Querydsl query type for ProjectAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectAlarm extends EntityPathBase<ProjectAlarm> {

    private static final long serialVersionUID = 271284993L;

    public static final QProjectAlarm projectAlarm = new QProjectAlarm("projectAlarm");

    public final NumberPath<Long> ino = createNumber("ino", Long.class);

    public final NumberPath<Long> pano = createNumber("pano", Long.class);

    public final NumberPath<Long> pno = createNumber("pno", Long.class);

    public QProjectAlarm(String variable) {
        super(ProjectAlarm.class, forVariable(variable));
    }

    public QProjectAlarm(Path<? extends ProjectAlarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectAlarm(PathMetadata metadata) {
        super(ProjectAlarm.class, metadata);
    }

}

