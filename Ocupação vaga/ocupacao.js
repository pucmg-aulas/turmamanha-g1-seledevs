// Dados simulados dos parques
const parques = {
    parque1: [
      { identificador: 'A1', tipo: 'Comum', status: 'Livre' },
      { identificador: 'A2', tipo: 'VIP', status: 'Ocupado' },
      { identificador: 'B1', tipo: 'Idoso', status: 'Livre' }
    ],
    parque2: [
      { identificador: 'C1', tipo: 'Comum', status: 'Ocupado' },
      { identificador: 'C2', tipo: 'VIP', status: 'Livre' },
      { identificador: 'D1', tipo: 'Idoso', status: 'Livre' }
    ],
    parque3: [
      { identificador: 'E1', tipo: 'Comum', status: 'Livre' },
      { identificador: 'E2', tipo: 'VIP', status: 'Ocupado' },
      { identificador: 'F1', tipo: 'Idoso', status: 'Livre' }
    ]
  };
  
  // Carrega as vagas com base no parque selecionado
  function loadVagas() {
    const parqueSelect = document.getElementById('parqueSelect').value;
    const vagas = parques[parqueSelect];
    const vagasTableBody = document.getElementById('vagasTableBody');
    vagasTableBody.innerHTML = '';
  
    vagas.forEach(vaga => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${vaga.identificador}</td>
        <td>${vaga.tipo}</td>
        <td class="${vaga.status === 'Livre' ? 'status-livre' : 'status-ocupado'}">${vaga.status}</td>
        <td>
          <button class="${vaga.status === 'Livre' ? 'reservar' : 'liberar'}" onclick="toggleOcupacao('${vaga.identificador}')">
            ${vaga.status === 'Livre' ? 'Reservar' : 'Liberar'}
          </button>
        </td>
      `;
      vagasTableBody.appendChild(row);
    });
  }
  
  // Alterna o status de ocupação da vaga
  function toggleOcupacao(identificador) {
    const parqueSelect = document.getElementById('parqueSelect').value;
    const vagas = parques[parqueSelect];
    const vaga = vagas.find(v => v.identificador === identificador);
  
    if (!vaga) return;
  
    // Altera o status e exibe alerta
    vaga.status = vaga.status === 'Livre' ? 'Ocupado' : 'Livre';
    const clienteTipo = document.getElementById('clienteSelect').value;
    
    alert(`Cliente ${clienteTipo} ${vaga.status === 'Ocupado' ? 'reservou' : 'liberou'} a vaga ${identificador}.`);
    
    // Recarrega a tabela para refletir o novo estado
    loadVagas();
  }
  
  // Carrega as vagas para o parque selecionado ao abrir a página
  window.onload = loadVagas;
  