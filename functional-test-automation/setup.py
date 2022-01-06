from setuptools import setup

setup(
    name='ekstep-test-automation',
    version='0.0.1',
    description='For test automation',
    packages=['clients',
              'features.models',
              'features.models.common',
              'features.models.jobs',
              'features.steps',
              'features.vars',
              'utils'
              ],
    install_requires=['requests',
                      'selenium',
                      'config'
                      ]
)
