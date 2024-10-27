const parques = {
    parque1: {
      comum: Array.from({ length: 20 }, (_, i) => ({ identificador: `C${i + 1}`, status: 'Livre' })),
      vip: Array.from({ length: 15 }, (_, i) => ({ identificador: `V${i + 1}`, status: 'Livre' })),
      idoso: Array.from({ length: 10 }, (_, i) => ({ identificador: `I${i + 1}`, status: 'Livre' })),
    },
    parque2: {
      comum: Array.from({ length: 20 }, (_, i) => ({ identificador: `C${i + 1}`, status: 'Livre' })),
      vip: Array.from({ length: 15 }, (_, i) => ({ identificador: `V${i + 1}`, status: 'Livre' })),
      idoso: Array.from({ length: 10 }, (_, i) => ({ identificador: `I${i + 1}`, status: 'Livre' })),
    },
    parque3: {
      comum: Array.from({ length: 20 }, (_, i) => ({ identificador: `C${i + 1}`, status: 'Livre' })),
      vip: Array.from({ length: 15 }, (_, i) => ({ identificador: `V${i + 1}`, status: 'Livre' })),
      idoso: Array.from({ length: 10 }, (_, i) => ({ identificador: `I${i + 1}`, status: 'Livre' })),
    },
  };
  
  // Função para mostrar a tabela com base na seleção de parque e tipo de vaga
  function mostrarTabela() {
    const parque = document.getElementById('parqueSelect').value;
    const tipoVaga = document.getElementById('tipoVagaSelect').value;
  
    const vagas = parques[parque][tipoVaga];
    const vagasTableBody = document.getElementById('vagasTableBody');
    const tipoVagaTitulo = document.getElementById('tipoVagaTitulo');
  
    tipoVagaTitulo.innerText = `Vagas ${tipoVaga.charAt(0).toUpperCase() + tipoVaga.slice(1)}`;
  
    // Limpa e preenche a tabela com as vagas do tipo selecionado
    vagasTableBody.innerHTML = '';
    vagas.forEach(vaga => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${vaga.identificador}</td>
        <td class="${vaga.status === 'Livre' ? 'status-livre' : 'status-ocupado'}">${vaga.status}</td>
        <td>
          <button class="${vaga.status === 'Livre' ? 'reservar' : 'liberar'}" onclick="toggleOcupacao('${vaga.identificador}', '${tipoVaga}')">
            ${vaga.status === 'Livre' ? 'Reservar' : 'Liberar'}
          </button>
        </td>
      `;
      vagasTableBody.appendChild(row);
    });
  
    // Exibe a tela de tabela e oculta a tela de seleção inicial
    document.getElementById('selectionScreen').style.display = 'none';
    document.getElementById('tableScreen').style.display = 'block';
  }
  
  // Função para alternar o status da vaga
  function toggleOcupacao(identificador, tipoVaga) {
    const parque = document.getElementById('parqueSelect').value;
    const clienteTipo = document.getElementById('clienteSelect').value;
  
    const vagas = parques[parque][tipoVaga];
    const vaga = vagas.find(v => v.identificador === identificador);
  
    if (!vaga) return;
  
    vaga.status = vaga.status === 'Livre' ? 'Ocupado' : 'Livre';
  
    alert(`Cliente ${clienteTipo} ${vaga.status === 'Ocupado' ? 'reservou' : 'liberou'} a vaga ${identificador}.`);
    
    mostrarTabela(); // Atualiza a tabela para refletir a mudança
  }
  
  // Função para voltar à tela de seleção inicial
  function voltar() {
    document.getElementById('selectionScreen').style.display = 'block';
    document.getElementById('tableScreen').style.display = 'none';
  }
  
