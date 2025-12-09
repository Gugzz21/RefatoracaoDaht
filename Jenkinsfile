pipeline {
    agent any

    stages {
        stage('Verificar Repositório') {
            steps {
                echo "1. Fazendo checkout do repositório Java Backend..."
                // Checkout do seu repositório Java Backend (que agora tem o index.html)
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs: [[url: 'https://github.com/Gugzz21/RefatoracaoDaht.git']]])
            }
        }

        stage('Unstash e Posicionar APK') {
            steps {
                script {
                    echo "2. Recuperando APK e substituindo artefato na pasta estática..."

                    // O diretório estático src/main/resources/static já deve existir no checkout.

                    // 2.1 Recupera o APK do pipeline do frontend (nome 'apk-artifact' é crucial)
                    // ATENÇÃO: Requer que o pipeline do Frontend tenha rodado primeiro!
                    try {
                        unstash 'apk-artifact'

                        // 2.2 Move e RENOMEIA o APK (app-release.apk) REAL para a pasta estática.
                        // Usando 'bat' e caminhos com barra invertida (\)
                        bat "move app-release.apk src\\main\\resources\\static\\DahtApp.apk"
                    } catch (Exception e) {
                        echo "⚠️ AVISO: Stash 'apk-artifact' não encontrado. O deploy seguirá sem a atualização do APK."
                        echo "Erro detalhado: ${e.getMessage()}"
                    }
                }
            }
        }

        stage('Empacotar Maven (Com APK e HTML)') {
            steps {
                script {
                    echo "3. Empacotando Maven (criando o JAR com assets)..."
                    // Não precisa atualizar PATH no Windows para comandos padrão
                    // O package agora incluirá os arquivos estáticos atualizados (APK e HTML) no JAR
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    echo "4. Construindo a imagem Docker..."
                    def appName = 'daht'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    // O build Docker usa o JAR recém-criado
                    bat "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Fazer Deploy') {
            steps {
                script {
                    echo "5. Fazendo deploy do novo container..."
                    def appName = 'daht'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    // Parar e remover o container existente, se houver
                    // Usando '|| exit 0' para evitar que o pipeline falhe se o container não existir
                    bat "docker stop ${appName} || exit 0"
                    bat "docker rm -v ${appName} || exit 0"

                    // Executar o novo container
                    bat "docker run -d --name ${appName} -p 8412:8412 ${imageTag}"
                }
            }
        }
    }
    post {
        success {
            echo '******************************************************'
            echo '✅ Deploy realizado com sucesso! O APK está disponível em /DahtApp.apk'
            echo '******************************************************'
        }
        failure {
            echo '******************************************************'
            echo '❌ Houve um erro durante o deploy.'
            echo '******************************************************'
        }
    }
}