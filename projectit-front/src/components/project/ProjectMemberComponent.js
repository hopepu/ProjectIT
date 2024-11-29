import React, {useEffect, useState} from "react";
import {Input, FormGroup, Label, ListGroup, ListGroupItem} from "reactstrap";
import {getList, postAdd} from "../../api/ProjectMemberApi";
import {useLocation, useNavigate, useParams} from "react-router-dom";
import ResultModal from "../projectModal/ResultModal";
import {list2, page} from "../../api/organizationAPI";
import FetchingModal from "../projectModal/FetchingModal";
import PageComponent from "../projectModal/PageComponent";


const MemberSearchComponent = () => {
    const MEMBER_ROLE_KOR = {
        CONTRACT_WORKER: "계약직",
        INTERN: "인턴",
        STAFF: "사원",
        ASSOCIATE: "주임",
        ASSISTANT_MANAGER: "대리",
        MANAGER: "과장",
        DEPUTY_MANAGER: "차장",
        GENERAL_MANAGER: "부장",
        DIRECTOR: "이사",
        SENIOR_DIRECTOR: "상무 이사",
        EXECUTIVE_VICE_PRESIDENT: "전무이사",
        PRESIDENT: "사장",
        VICE_CHAIRMAN: "부회장",
        CHAIRMAN: "회장",
        CEO: "대표이사",
    };

    const MEMBER_TEAM_KOR = {
        AWAIT: "대기",
        TECHNIC: "기술팀",
        PERSONNEL: "인사팀",
        ACCOUNTING: "회계팀",
        FINANCIAL_MANAGEMENT: "재무관리팀"
    }


    // 파라미터에서 pno를 가져와 사용
    const {pno} = useParams();
    // 불러온 멤버 리스트
    const [member, setMember] = useState([]);
    // 멤버 선택
    const [selectedMembers, setSelectedMembers] = useState([]);
    // 필터링 멤버 목록
    const [searchTerm, setSearchTerm] = useState("");
    // 결과 모달
    const [result, setResult] = useState(null)
    // 이동 관련
    const navigate = useNavigate();
    // fetching
    const [fetching, setFetching] = useState(false)
    // 불러온 데이터
    const [serverData, setServerData] = useState([]);
    // 필터링된 멤버
    const filteredMembers = member.filter(
        (member) =>
            (member.name && member.name.includes(searchTerm)) ||
            (MEMBER_TEAM_KOR[member.team] && MEMBER_TEAM_KOR[member.team].includes(searchTerm)) ||
            (MEMBER_ROLE_KOR[member.memberRole] && MEMBER_ROLE_KOR[member.memberRole].includes(searchTerm))
    );
    // url이나 storage에서 정보 가져올때 씀
    const location = useLocation();

    const queryParams = new URLSearchParams(location.search);
    const currentPage = parseInt(queryParams.get("page")) || 1; // 기본값 1
    const currentSize = parseInt(queryParams.get("size")) || 10; // 기본값 10

    // 멤버 선택 핸들러
    const handleSelectMember = (member) => {
        getList(pno).then(data => {
            const pmno = data.some(m => m.mno === member.mno);
            if (pmno) {
                alert("이미 참여중인 멤버입니다.")
            } else {
                const isSelected = selectedMembers.some((m) => m.mno === member.mno);
                const updatedMembers = isSelected
                    ? selectedMembers.filter((m) => m.mno !== member.mno)
                    : [...selectedMembers, member];
                setSelectedMembers(updatedMembers);
            }
        });
    };


    // 멤버 추가 버튼 클릭시
    const handleClickAdd = () => {
        postAdd(selectedMembers, pno).then(data =>
            setResult(data))
    }

    // 모달 창 닫기
    const closeModal = () => {
        setResult(null)
        navigate({pathname:`../project/${pno}`})
    }

    // 데이터 업데이트
    const moveToList = (pageParam) => {
        // 기존의 쿼리 파라미터 값 유지
        const currentPage = queryParams.get("page") || 1;
        const currentSize = queryParams.get("size") || 10;

        // pageParam이 있을 때만 페이지 변경
        const pageNum = pageParam?.page || currentPage;
        const sizeNum = pageParam?.size || currentSize;

        // 페이지와 크기를 바탕으로 member 데이터를 갱신
        setFetching(true); // 로딩 시작
        page({ page: pageNum, size: sizeNum }).then((data) => {
            setServerData(data); // 데이터를 상태로 업데이트
            setFetching(false); // 로딩 종료
        });
    };

    // 간혹 모달이 안닫히는 경우가 있어 추가함
    useEffect(() => {
        if (fetching) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = 'auto';
        }
    }, [fetching]);

    // 여기는 페이징처리된 멤버리스트
    useEffect(() => {
        page({ page:currentPage, size:currentSize }).then(data => {
            setServerData(data);
        })
    }, [])
    
    // 여기는 전체 리스트
    useEffect(() => {
        list2().then(data => {
            setMember(data)
        })
    }, [])



    return (
        <div>
            <>
                <FetchingModal isOpen={fetching}/>
                {/* serverData와 approver를 사용하는 UI */}
            </>
            <ResultModal
                isOpen={result === "SUCCESS"}
                content="멤버 등록 완료"
                callbackFn={() => closeModal()}
            />
            <p>멤버는 추후에 추가, 삭제 가능하며, 작성자(팀장)은 자동으로 등록됩니다.</p>
            <FormGroup>
                <Label>멤버 검색</Label>

                <Input
                    type="text"
                    placeholder="이름 또는 팀으로 검색"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
                {/* 검색어가 있을 때만 연관 검색어 목록 표시 */}
                {(
                    <ListGroup className="mt-2">
                        {(searchTerm ? filteredMembers : serverData.dtoList || []).map((member) => (
                            <ListGroupItem
                                key={member.mno}
                                action
                                active={selectedMembers.some((m) => m.mno === member.mno)}
                                onClick={() => handleSelectMember(member)}
                            >
                                {member.name} {MEMBER_ROLE_KOR[member.memberRole] || member.memberRole} -
                                ({MEMBER_TEAM_KOR[member.team] || member.team})
                            </ListGroupItem>
                        ))}
                        {(searchTerm ? filteredMembers : serverData.dtoList || []).length === 0 && (
                            <ListGroupItem disabled>일치하는 멤버가 없습니다</ListGroupItem>
                        )}
                    </ListGroup>
                )}
            </FormGroup>
            <PageComponent serverData={serverData} movePage={moveToList}/>
            {/* 선택된 멤버 목록 표시 */}
            <div>
                <Label>선택된 멤버</Label>
                <ListGroup>
                    {selectedMembers.map((member) => (
                        <ListGroupItem key={member.id}>
                            {member.name} {MEMBER_ROLE_KOR[member.memberRole] || member.memberRole} -
                            ({MEMBER_TEAM_KOR[member.team] || member.team})
                        </ListGroupItem>
                    ))}
                    {selectedMembers.length === 0 && (
                        <ListGroupItem disabled>선택된 멤버가 없습니다</ListGroupItem>
                    )}
                </ListGroup>

            </div>

            <button className={"btn btn-primary"}
                    onClick={handleClickAdd}>저장
            </button>
        </div>
    );
};

export default MemberSearchComponent;