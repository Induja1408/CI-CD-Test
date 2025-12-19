pipeline {
    agent any

    environment {
        ANSIBLE_CONFIG = "${WORKSPACE}/ansible/ansible.cfg"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Cloning GitHub repository'
                git branch: 'main',
                    url: 'https://github.com/Induja1408/CI-CD-Test.git'
            }
        }

        stage('Verify Ansible') {
            steps {
                sh '''
                echo "Checking Ansible version"
                ansible --version
                '''
            }
        }

        stage('Run Ansible Playbook') {
            steps {
                sh '''
                echo "Running Ansible playbook on EC2"
                cd ansible
                ansible-playbook -i inventory.ini playbook.yml
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Deployment completed successfully'
        }
        failure {
            echo '❌ Deployment failed'
        }
    }
}
