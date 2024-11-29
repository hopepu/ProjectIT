package com.project.it.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatUsers is a Querydsl query type for ChatUsers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatUsers extends EntityPathBase<ChatUsers> {

    private static final long serialVersionUID = -713639449L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatUsers chatUsers = new QChatUsers("chatUsers");

    public final QChatRooms chatRooms;

    public final NumberPath<Long> cuno = createNumber("cuno", Long.class);

    public final QProjectMember projectMember;

    public QChatUsers(String variable) {
        this(ChatUsers.class, forVariable(variable), INITS);
    }

    public QChatUsers(Path<? extends ChatUsers> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatUsers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatUsers(PathMetadata metadata, PathInits inits) {
        this(ChatUsers.class, metadata, inits);
    }

    public QChatUsers(Class<? extends ChatUsers> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRooms = inits.isInitialized("chatRooms") ? new QChatRooms(forProperty("chatRooms"), inits.get("chatRooms")) : null;
        this.projectMember = inits.isInitialized("projectMember") ? new QProjectMember(forProperty("projectMember"), inits.get("projectMember")) : null;
    }

}

