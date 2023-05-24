--
-- Banco de dados: `empresabiancovilli`
--
CREATE DATABASE IF NOT EXISTS bdempresa;
USE bdempresa;

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamentohibernate`
--

CREATE TABLE departamentohibernate (
  IdcodDepto int(11) NOT NULL,
  descricao varchar(255) DEFAULT NULL
);

--
-- Extraindo dados da tabela departamentohibernate
--

INSERT INTO departamentohibernate (IdcodDepto, descricao) VALUES (1, 'd');

-- --------------------------------------------------------

--
-- Estrutura da tabela funcionariohibernate
--

CREATE TABLE funcionariohibernate (
  Idmatricula int(11) NOT NULL,
  nome varchar(255) DEFAULT NULL,
  nascimento datetime DEFAULT NULL,
  salario double NOT NULL,
  cargo varchar(255) DEFAULT NULL,
  ceIdcodDepto int(11) DEFAULT NULL
);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela departamentohibernate
--
ALTER TABLE departamentohibernate
  ADD PRIMARY KEY (IdcodDepto);

--
-- Índices para tabela funcionariohibernate
--
ALTER TABLE funcionariohibernate
  ADD PRIMARY KEY (Idmatricula),
  ADD KEY FK91BA34D723FC5355 (ceIdcodDepto);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela departamentohibernate
--
ALTER TABLE departamentohibernate
  MODIFY IdcodDepto int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela funcionariohibernate
--
ALTER TABLE funcionariohibernate
  MODIFY Idmatricula int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela funcionariohibernate
--
ALTER TABLE funcionariohibernate
  ADD CONSTRAINT FK91BA34D723FC5355 FOREIGN KEY (ceIdcodDepto) REFERENCES departamentohibernate (IdcodDepto);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;