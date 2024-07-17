/*document.addEventListener("DOMContentLoaded", function() {
    loadPosts();
});

function openPostForm() {
    document.getElementById('postModal').style.display = "flex";
}

function closePostForm() {
    document.getElementById('postModal').style.display = "none";
}

function openViewModal(post) {
    document.getElementById('view-title').textContent = post.freeTitle;
    document.getElementById('view-author').textContent = `글쓴이: ${post.memberNo}`;
    document.getElementById('view-timestamp').textContent = `작성시간: ${post.freeCreatedAt}`;
    document.getElementById('view-content').textContent = post.freeContent;
    document.getElementById('viewModal').style.display = "flex";
}

function closeViewModal() {
    document.getElementById('viewModal').style.display = "none";
}

async function addPost() {
    const title = document.getElementById('post-title').value.trim();
    const author = document.getElementById('post-author').value.trim();
    const content = document.getElementById('post-content').value.trim();

    if (title && author && content) {
        const post = {
            freeTitle: title,
            freeContent: content,
            memberNo: parseInt(author),
            freeCreatedAt: new Date().toISOString(),
            freeViewCount: 0,
            freeFileName: '',
            fbanswerNo: 0
        };

        try {
            await fetch('/api/freeboard/articles', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(post)
            });
            loadPosts();
            closePostForm();
        } catch (error) {
            console.error('Error adding post:', error);
        }
    } else {
        alert('모든 필드를 입력하세요.');
    }
}

async function loadPosts() {
    try {
        const response = await fetch('/freeboard/articles');
        const posts = await response.json();
        const postList = document.getElementById('post-list');
        postList.innerHTML = ''; // Clear the current list

        posts.forEach((post, index) => appendPost(post, index));
    } catch (error) {
        console.error('Error loading posts:', error);
    }
}

function appendPost(post, index) {
    const postList = document.getElementById('post-list');
    const row = document.createElement('tr');
    row.setAttribute('data-id', post.freePostNo);
    row.addEventListener('click', function() {
        incrementViews(post.freePostNo);
        openViewModal(post);
    });

    row.innerHTML = `
        <td>${index + 1}</td>
        <td>${post.freeTitle}</td>
        <td>${post.memberNo}</td>
        <td>${post.freeCreatedAt}</td>
        <td id="views-${post.freePostNo}">${post.freeViewCount}</td>
    `;

    postList.insertBefore(row, postList.firstChild); // New posts appear at the top
}

async function incrementViews(postId) {
    try {
        const response = await fetch(`/api/freeboard/articles/${postId}`);
        const post = await response.json();
        post.freeViewCount += 1;

        await fetch('/api/freeboard/articles', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post)
        });

        document.getElementById(`views-${postId}`).textContent = post.freeViewCount;
    } catch (error) {
        console.error('Error incrementing views:', error);
    }
}
*/