function fetchAlunos() {
    fetch('/aluno/listar')
        .then(response => response.json())
        .then(alunos => {
            const tabelaAlunos = document.querySelector('#alunoTabela tbody');
            tabelaAlunos.innerHTML = '';

            alunos.forEach(aluno => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${aluno.nome}</td>
                    <td>${aluno.matricula}</td>
                    <td>
                        <button class="btn-delete" onclick="deletarAluno('${aluno.matricula}')">Deletar</button>
                        <button onclick="mostrarDadosAluno('${aluno.matricula}')">Dados</button>
                    </td>
                `;
                tabelaAlunos.appendChild(tr);
            });
        });
}

// Função para deletar o aluno
function deletarAluno(matricula) {
    fetch(`/aluno/deletar/${matricula}`, { method: 'DELETE' })
        .then(() => fetchAlunos());
}

// Função para exibir os dados do aluno no pop-up ao clicar no botão DADOS
function mostrarDadosAluno(matricula) {
    fetch(`/aluno/obter/${matricula}`)
        .then(response => response.json())
        .then(aluno => {
            // Matrícula (Chave) deve ser imutável
            document.getElementById('popup-matricula').innerText = aluno.matricula;
            document.getElementById('popup-nome').value = aluno.nome;
            document.getElementById('popup-idade').value = aluno.idade;
            document.getElementById('popup-email').value = aluno.email;

            document.getElementById('popup').style.display = 'block';
        });
}



document.addEventListener('DOMContentLoaded', function() {
    const alunoForm = document.getElementById('alunoForm');
    const popupForm = document.getElementById('popupForm');

    // Exibe a lista de alunos na página inicial
    fetchAlunos();

    // Lógica para realizar a adição de um aluno novo a partir do formulário (POST)
    alunoForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const matricula = parseInt(document.getElementById('matricula').value.trim(), 10);
        const nome = document.getElementById('nome').value.trim();
        const idade = parseInt(document.getElementById('idade').value.trim(), 10);
        const email = document.getElementById('email').value.trim();

        if (!isNaN(matricula) && nome && !isNaN(idade) && email) {
            fetch('/aluno/inserir', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ matricula, nome, idade, email })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao salvar aluno: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                alert('Aluno salvo com sucesso!');
                fetchAlunos();
                alunoForm.reset();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Erro ao salvar aluno. Tente novamente.');
            });
        } else {
            alert('Preencha todos os campos corretamente!');
        }
    });

    // Lógica para realizar a edição dos dados de um aluno já existente (PUT)
    popupForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const matricula = parseInt(document.getElementById('popup-matricula').innerText);
        const nome = document.getElementById('popup-nome').value.trim();
        const idade = parseInt(document.getElementById('popup-idade').value.trim(), 10);
        const email = document.getElementById('popup-email').value.trim();

        if (matricula && nome && !isNaN(idade) && email) {
            fetch('/aluno/alterar', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ matricula, nome, idade, email })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao atualizar aluno: ' + response.statusText);
                }
                alert('Aluno atualizado com sucesso!');
                fetchAlunos(); // Recarrega a lista de alunos
                document.getElementById('popup').style.display = 'none'; // Fecha o pop-up
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao atualizar aluno. Tente novamente.');
            });     
        } else {
            alert('Preencha todos os campos corretamente!');
        }
    });


    // Popular a tabela com todos os alunos existentes no banco utilizando a função listar da API (GET)
    

    
    
    // Fechar o pop-up ao clicar no "x"
    document.querySelector('.close').onclick = function() {
        document.getElementById('popup').style.display = 'none';
    }
    
    // Fechar o pop-up ao clicar fora dele
    window.onclick = function(event) {
        if (event.target == document.getElementById('popup')) {
            document.getElementById('popup').style.display = 'none';
        }
    }
});