package com.practice.demoCommunity.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.demoCommunity.dto.CommunityUser;
import com.practice.demoCommunity.dto.Gallery;
import com.practice.demoCommunity.dto.GalleryBoard;
import com.practice.demoCommunity.dto.GalleryClassification;
import com.practice.demoCommunity.dto.GalleryComment;
import com.practice.demoCommunity.dto.GalleryCommentLike;
import com.practice.demoCommunity.dto.GalleryPost;
import com.practice.demoCommunity.dto.GalleryPostLike;
import com.practice.demoCommunity.repository.CommunityUserRepository;
import com.practice.demoCommunity.repository.GalleryBoardRepository;
import com.practice.demoCommunity.repository.GalleryClassificationRepository;
import com.practice.demoCommunity.repository.GalleryCommentLikeRepository;
import com.practice.demoCommunity.repository.GalleryCommentRepository;
import com.practice.demoCommunity.repository.GalleryPostLikeRepository;
import com.practice.demoCommunity.repository.GalleryPostRepository;
import com.practice.demoCommunity.repository.GalleryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityTestService {

	private final CommunityUserRepository communityUserRepository;
	private final GalleryClassificationRepository galleryClassificationRepository;
	private final GalleryRepository galleryRepository;
	private final GalleryBoardRepository galleryBoardRepository;
	private final GalleryPostRepository galleryPostRepository;
	private final GalleryPostLikeRepository galleryPostLikeRepository;
	private final GalleryCommentRepository galleryCommentRepository;
	private final GalleryCommentLikeRepository galleryCommentLikeRepository;
	
	private String[] commonGalleryBoards = { "공지사항", "자유게시판", "토론게시판" };


	public void insertTestDummyData100() {
		// 일반 유저 데이터 생성
		List<CommunityUser> commonUserList = createDummyCommonUsers();
		communityUserRepository .saveAll( commonUserList );
		
		// 갤러리 관리자 유저 데이터 생성
		List<CommunityUser> galleryAdminUserList = createDummyGalleryAdminUsers();
		communityUserRepository .saveAll( galleryAdminUserList );
		
		// 갤러리 분류 생성
		List<GalleryClassification> galleryClassificationList = createDummyGalleryClassifications();
		galleryClassificationRepository .saveAll( galleryClassificationList );

		// 갤러리 데이터 생성
		List<Gallery> galleryList = createDummyGalleries(galleryClassificationList, galleryAdminUserList);
		galleryRepository.saveAll( galleryList );
		
		// 갤러리 게시판 데이터 생성
		List<GalleryBoard> galleryBoardList = createDummyGalleryBoards(galleryList);
		galleryBoardRepository.saveAll( galleryBoardList );

		// 갤러리 게시판의 게시물 데이터 생성
		List<GalleryPost> galleryPostList = createDummyGalleryPosts(galleryBoardList, commonUserList);
		galleryPostRepository.saveAll( galleryPostList );
		
		// 게시물 좋아요 기록 데이터 생성
		List<GalleryPostLike> galleryPostLikeList = createDummyGalleryPostLikes(galleryPostList);
		galleryPostLikeRepository.saveAll( galleryPostLikeList );
		
		// 각 게시물에 대한 댓글 데이터 생성
		List<GalleryComment> galleryCommentList = createDummyGalleryComments(galleryPostList);
		galleryCommentRepository.saveAll( galleryCommentList );
		
		// 댓글에 좋아요 기록 데이터 생성
		List<GalleryCommentLike> galleryCommentLikeList = createDummyGalleryCommentLikes(galleryCommentList);
		galleryCommentLikeRepository.saveAll( galleryCommentLikeList  );
	}


	public List<CommunityUser> createDummyGalleryAdminUsers() {
		List<CommunityUser> dummyGalleryAdminUserList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			dummyGalleryAdminUserList .add( CommunityUser .builder()
					.userId( "galleryAdminUser" + i )
					.password("1234")
					.email( "galleryAdminUser" + i + "@test.com" )
					.created( LocalDateTime.now() )
					.build() );
		}
		return dummyGalleryAdminUserList;
	}


	public List<CommunityUser> createDummyCommonUsers() {
		List<CommunityUser> dummyCommonUserList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			dummyCommonUserList .add( CommunityUser .builder()
					.userId( "CommonUser" + i )
					.password("1234")
					.email( "commonUser" + i + "@test.com" )
					.created( LocalDateTime.now() )
					.build() );
		}
		return dummyCommonUserList;
	}


	public List<GalleryClassification> createDummyGalleryClassifications() {
		List<GalleryClassification> dummyGalleryClassificationList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			dummyGalleryClassificationList .add( GalleryClassification .builder()
					.name( i + "번째 갤러리 분류" )
					.build() );
		}
		return dummyGalleryClassificationList;
	}


	public List<Gallery> createDummyGalleries(List<GalleryClassification> dummyGalleryClassificationList, List<CommunityUser> dummyGalleryAdminUserList) {
		List<Gallery> dummyGalleryList = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			GalleryClassification classification = dummyGalleryClassificationList .get(i-1);
			CommunityUser admin = dummyGalleryAdminUserList .get(i-1);
			dummyGalleryList .add( Gallery .builder()
					.galleryClassification( classification )
					.name( i + "번째" )
					.discription( i + "번째 갤러리 소개글입니다.")
					// 현재 for문에서 생성된 갤러리의 번호와 동일한 galleryAdmin을 관리자로 설정
					.galleryAdmin( admin )
					.build() );
		}
		return dummyGalleryList;
	}
	
	
	private List<GalleryBoard> createDummyGalleryCommonBoards(List<GalleryBoard> dummyGalleryBoardList, Gallery gallery) {
		Gallery insertGalleryEntity = galleryRepository .findById( gallery .getId() )
				.orElse(null);
		
		for (int i = 1; i <= commonGalleryBoards.length; i++) {
			String boardName = commonGalleryBoards[i-1];
			// 공통 게시판 데이터 삽입
			dummyGalleryBoardList .add( GalleryBoard .builder()
					.fromGallery( insertGalleryEntity )
					.name( boardName )
					.created( LocalDateTime .now() )
					.orderNum(i)
					.build() );
		}
		return dummyGalleryBoardList;
	}


	public List<GalleryBoard> createDummyGalleryBoards(List<Gallery> dummyGalleryList) {
		List<GalleryBoard> dummyGalleryBoardList = new ArrayList<>();
		int commonGalleryBoardCount = commonGalleryBoards .length;
		int totalBoardCount = 5;
		// 10개의 갤러리에 10개의 게시판 추가
		for (Gallery gallery : dummyGalleryList) {
			// 각 갤러리별 게시판 10개 추가
			// 단, 공통 게시판 삽입 이후부터 진행
			dummyGalleryBoardList = createDummyGalleryCommonBoards(dummyGalleryBoardList, gallery);
			for (int j = 1; j <= totalBoardCount - commonGalleryBoardCount; j++) {
				dummyGalleryBoardList .add( GalleryBoard .builder()
						// 변수 gallery에 순서대로 담기는 갤러리에 j번째 게시판 추가
						.fromGallery( gallery )
						.name( j + "번째 게시판" )
						.created( LocalDateTime .now() )
						.orderNum( commonGalleryBoardCount + j )
						.build() );
			}
		}
		return dummyGalleryBoardList;
	}


	public List<GalleryPost> createDummyGalleryPosts(List<GalleryBoard> dummyGalleryBoardList, List<CommunityUser> dummyCommonUserList) {
		List<GalleryPost> dummyGalleryPostList = new ArrayList<>();
		// i번째 게시판에 j번째 게시물을 j번째 유저 아이디로 작성
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				dummyGalleryPostList .add( GalleryPost .builder()
						.title( j + "번째 글" )
						.content( j + "번째 글 내용입니다." )
						.author( dummyCommonUserList .get(j-1) )
						.galleryBoard( dummyGalleryBoardList .get(i-1) )
						.created( LocalDateTime.now() )
						.build());
			}
		}
		return dummyGalleryPostList;
	}


	public List<GalleryPostLike> createDummyGalleryPostLikes(List<GalleryPost> dummyGalleryPostList) {
		List<GalleryPostLike> dummyGalleryPostLikeList = new ArrayList<>();
		// 각 게시물에 작성자 유저 아이디로 좋아요 처리
		for (int i = 1; i <= 100; i++) {
			GalleryPost post = dummyGalleryPostList .get(i-1);
			CommunityUser author = post .getAuthor();
			dummyGalleryPostLikeList .add( GalleryPostLike .builder()
					.galleryPost( post )
					.fromUser(author)
					.created( LocalDateTime.now() )
					.build() );
		}
		return dummyGalleryPostLikeList;
	}


	public List<GalleryComment> createDummyGalleryComments(List<GalleryPost> dummyGalleryPostList) {
		List<GalleryComment> dummyGalleryCommentList = new ArrayList<>();
		// 각 게시물 작성자가 스스로 댓글을 작성
		for (int i = 1; i <= 100; i++) {
			GalleryPost post = dummyGalleryPostList .get(i-1);
			CommunityUser author = post .getAuthor();
			dummyGalleryCommentList .add( GalleryComment .builder()
					.galleryPost( post )
					.author(author)
					.content( i + "번째 댓글입니다." )
					.created( LocalDateTime.now() )
					.build() );
		}
		return dummyGalleryCommentList;
	}


	public List<GalleryCommentLike> createDummyGalleryCommentLikes(List<GalleryComment> dummyGalleryCommentList) {
		List<GalleryCommentLike> dummyGalleryCommentLikeList = new ArrayList<>();
		// 각 댓글의 작성자가 스스로 좋아요를 클릭
		for (int i = 1; i <= 100; i++) {
			GalleryComment comment = dummyGalleryCommentList .get(i-1);
			CommunityUser author = comment .getAuthor();
			dummyGalleryCommentLikeList .add( GalleryCommentLike .builder()
					.galleryComment(comment)
					.fromUser(author)
					.created( LocalDateTime.now() )
					.build() );
		}
		return dummyGalleryCommentLikeList;
	}

}
