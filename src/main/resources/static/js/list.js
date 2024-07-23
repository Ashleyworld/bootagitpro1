document.addEventListener('DOMContentLoaded', function() {
    axios.get('/api/list') // 서버에서 작업목록을 가져옴
        .then(response => {
            const tasks = response.data; // JSON데이터를 가져옴
            const taskList = document.getElementById('taskList');
            const message = document.getElementById('message');

            // 작업 목록을 HTML에 추가합니다.
            tasks.forEach(task => {
                const listItem = document.createElement('li');
                listItem.textContent = `${task.name} - ${task.description}`;
                taskList.appendChild(listItem);
            });

            // 성공 메시지 표시 (ㅇㅖ : URL 파라미터나 서버 응답에서 가져온 메세지)
            if (response.data.message){
                message.textContent = response.data.message;
            }



        })
        .catch(error => {
            console.error('Eroor fetching tasks:', error);
        })
})