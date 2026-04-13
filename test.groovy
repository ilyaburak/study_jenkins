pipeline {
    agent {node ('мастер')}
    stages {
        stage ('Download from git'){
            steps {
                script {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/master']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [[$class: 'RelativeTargetDirectory',
                    relativeTargetDir: 'test']],
                    submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId: 'git_cred',url: 'https://github.com/ilyaburak/study_jenkins.git']]])
                
                }}
        }
        stage ('run shell command') {
            steps{
                script{
                    sh 'ps -ef'
                    currentBuild.result = 'FAILURE'
                }
            }
        }
       
    }
    
}
